import { SummaryService } from './sales/model/summary.service';
import { SummaryController } from './sales/controller/summary.controller';
import { SalesModule } from './sales/sales.module';
import { MasterModule } from './master/master.module';
import { EmployeeModule } from './employee/employee.module';
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { MulterModule } from '@nestjs/platform-express';

@Module({
  imports: [
    TypeOrmModule.forRoot(),
    MasterModule, 
    EmployeeModule, 
    SalesModule, 
    MulterModule
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
