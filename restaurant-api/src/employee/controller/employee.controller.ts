import { ClassSerializerInterceptor, Controller, Get, Query, UseInterceptors } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Employee } from '../model/employee.entity';
import { EmployeeService } from '../model/employee.service';

@Controller("employees")
export class EmployeeController extends BaseControllerMutable<Employee> {

    constructor(private readonly empService:EmployeeService) {
        super(empService, '/employees')
    }

    @Get('search')
    @UseInterceptors(ClassSerializerInterceptor)
    search(@Query('name') name:string):Promise<Employee[]> {
        return this.empService.findByNameLike(name)
    }
}
