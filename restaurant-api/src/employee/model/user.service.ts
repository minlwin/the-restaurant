import { Injectable, OnModuleInit } from '@nestjs/common';
import { Repository } from 'typeorm';
import { Employee } from './employee.entity';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class UserService implements OnModuleInit{

    constructor(
        @InjectRepository(Employee)
        private readonly repo:Repository<Employee>
    ){}

    async onModuleInit() {
        let admin = await this.findByLoginId('Admin')

        if(!admin) {
            admin = new Employee()
            admin.name = 'Admin'
            admin.email = 'Admin'
            admin.role = 'Admim'
            admin.phone = ''
            admin.password = 'Admin'

            await this.repo.save(admin)
        }
    }

    findByLoginId(loginId:string) {
        return this.repo.findOne({email: loginId})
    }
}
