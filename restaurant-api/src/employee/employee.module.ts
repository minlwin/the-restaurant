import { EmployeeService } from './model/employee.service';
import { EmployeeController } from './controller/employee.controller';
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm'
import { Employee } from './model/employee.entity'
import { UserService } from './model/user.service';

@Module({
    imports: [
        TypeOrmModule.forFeature([Employee]),
    ],
    controllers: [
        EmployeeController, ],
    providers: [
        EmployeeService,
        UserService
    ],
    exports: [
        UserService
    ]
})
export class EmployeeModule {}
