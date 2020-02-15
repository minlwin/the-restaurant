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

    findByNameLike(name:String) {

        if(!name) {
            return this.repo.find()
        }

        return this.repo.createQueryBuilder()
            .where('lower(name) like lower(:name)', {name: `%${name.toLowerCase()}%s`}).getMany()
    }
}
