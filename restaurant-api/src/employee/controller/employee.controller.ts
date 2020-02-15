import { Controller, Get, Query } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Employee } from '../model/employee.entity';
import { EmployeeService } from '../model/employee.service';

@Controller("employees")
export class EmployeeController extends BaseControllerMutable<Employee> {

    constructor(private readonly empService:EmployeeService) {
        super(empService, '/employees')
    }

    @Get('search')
    search(@Query('name') name:string) {
        return this.empService.findByNameLike(name)
    }
}
