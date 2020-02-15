import { Controller, Get, Query } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Employee } from '../model/employee.entity';
import { EmployeeService } from '../model/employee.service';

@Controller("employees")
export class EmployeeController extends BaseControllerMutable<Employee> {

    constructor(service:EmployeeService) {
        super(service, '/employees')
    }

    @Get('search')
    search(@Query('name') name:string) {

    }
}
