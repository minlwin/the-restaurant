import { Repository } from "typeorm";
import { IdEnable } from "./id.enable";

export class BaseService<T extends IdEnable> {

    constructor(protected readonly repo:Repository<T>) {}

    findAll() {
        return this.repo.find()
    }

    findById(id:number) {
        return this.repo.findOne(id)
    }

}