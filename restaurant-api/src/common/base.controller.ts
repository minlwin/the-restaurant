import { BaseService } from "./base.service";
import { Get, Param, Post, Body, Put, UseInterceptors, ClassSerializerInterceptor, Res } from "@nestjs/common";
import { IdEnable } from "./id.enable";

export class BaseController<T extends IdEnable> {

    constructor(protected readonly service:BaseService<T>) {}

    @Get()
    @UseInterceptors(ClassSerializerInterceptor)
    index() {
        return this.service.findAll()
    }

    @Get(':id')
    @UseInterceptors(ClassSerializerInterceptor)
    findById(@Param('id') id:number) {
        return this.service.findById(id)
    }

}