import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { SaleDetails } from './saledetails.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';

@Injectable()
export class SaleDetailsService extends BaseService<SaleDetails> {

    constructor(
        @InjectRepository(SaleDetails)
        repo:Repository<SaleDetails>
    ) { super(repo) }

}
