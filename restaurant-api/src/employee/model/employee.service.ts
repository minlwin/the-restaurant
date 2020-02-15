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
        return this.repo.createQueryBuilder()
            .where('LOWER(name) like :name', { name : `%${name.toLocaleLowerCase()}%` }).getMany()
    }
}
