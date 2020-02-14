import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseServiceMutable } from 'src/common/base.service.mutable';
import { Repository } from 'typeorm';
import { Tables } from './tables.entity';

@Injectable()
export class TablesService extends BaseServiceMutable<Tables> {

    constructor(
        @InjectRepository(Tables)
        repo:Repository<Tables>
    ) { super(repo) }

}
