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

    search(tableNumber:string) {
        return this.repo.createQueryBuilder()
            .where('LOWER(tableNumber) like :tableNumber', 
                {tableNumber : `%${tableNumber.toLocaleLowerCase()}%`})
            .getMany()
    }

    upload(tables:Tables[]) {
        return this.repo.save(tables)
    }

    findActiveCount(ids:any[]) {
        if(ids.length > 0)  {
            return this.repo.createQueryBuilder().where('id not in (:...ids)', {ids : ids}).getCount()
        }

        return this.repo.count()
    }
}
