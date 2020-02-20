import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseServiceMutable } from 'src/common/base.service.mutable';
import { Repository } from 'typeorm';
import { Employee } from './employee.entity';

@Injectable()
export class EmployeeService extends BaseServiceMutable<Employee> {
    constructor(
        @InjectRepository(Employee)
        repo:Repository<Employee>
    ) {
        super(repo)
    }

    search(name:string, phone:string) {

        let query = this.repo.createQueryBuilder().where('1 = 1')

        if(name) {
            query = query.andWhere('LOWER(name) like :name', { name : `%${name.toLocaleLowerCase()}%` })
        }

        if(phone) {
            query = query.andWhere('phone like :phone', {phone : `${phone}%`})
        }

        return query.getMany()
    }
}
