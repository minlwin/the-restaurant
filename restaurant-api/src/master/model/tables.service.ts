import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { Tables } from './tables.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';

@Injectable()
export class TablesService extends BaseService<Tables> {

    constructor(
        @InjectRepository(Tables)
        repo:Repository<Tables>
    ) { super(repo) }

}
