import { Repository } from "typeorm";
import { IdEnable } from "./id.enable";
import { BaseService } from "./base.service";

export class BaseServiceMutable<T extends IdEnable> extends BaseService<T> {

    constructor(repo:Repository<T>) {
        super(repo)
    }

    save(t:T) {
        return this.repo.save(t)
    }
}