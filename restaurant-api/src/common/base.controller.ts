import { ClassSerializerInterceptor, Get, Param, UseInterceptors, UseGuards } from "@nestjs/common";
import { BaseService } from "./base.service";
import { IdEnable } from "./id.enable";
import { JwtAuthGuard } from "src/auth/model/jwt-auth.guard";

export class BaseController<T extends IdEnable> {

    constructor(protected readonly service:BaseService<T>) {}

    @Get()
    @UseGuards(JwtAuthGuard)
    @UseInterceptors(ClassSerializerInterceptor)
    index() {
        return this.service.findAll()
    }

    @Get(':id')
    @UseGuards(JwtAuthGuard)
    @UseInterceptors(ClassSerializerInterceptor)
    findById(@Param('id') id:number) {
        return this.service.findById(id)
    }

}