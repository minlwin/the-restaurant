import { ClassSerializerInterceptor, Controller, Get, Query, UseInterceptors, UseGuards } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Employee } from '../model/employee.entity';
import { EmployeeService } from '../model/employee.service';
import { JwtAuthGuard } from 'src/auth/model/jwt-auth.guard';

@Controller("employees")
export class EmployeeController extends BaseControllerMutable<Employee> {

    constructor(private readonly empService:EmployeeService) {
        super(empService, '/employees')
    }

    @Get('search')
    @UseGuards(JwtAuthGuard)
    @UseInterceptors(ClassSerializerInterceptor)
    search(@Query('name') name:string, @Query('phone') phone:string):Promise<Employee[]> {
        return this.empService.search(name, phone)
    }
}
