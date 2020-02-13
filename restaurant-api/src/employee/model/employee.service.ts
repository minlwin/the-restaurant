import { Injectable } from '@nestjs/common';
import { Repository, Like } from 'typeorm';
import { Employee } from './employee.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { BaseService } from 'src/common/base.service';

@Injectable()
export class EmployeeService extends BaseService<Employee> {
    constructor(
        @InjectRepository(Employee)
        repo:Repository<Employee>
    ) {
        super(repo)
    }

    findByNameLike(name:String) {
        this.repo.createQueryBuilder()
            .where('lower(name) like lower(:name)', {name: `%${name.toLowerCase()}%s`}).getMany()
    }
}
