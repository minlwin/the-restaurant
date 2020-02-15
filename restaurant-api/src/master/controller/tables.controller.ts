import { Controller } from '@nestjs/common';
import { BaseControllerMutable } from 'src/common/base.controller.mutable';
import { Tables } from '../model/tables.entity';
import { TablesService } from '../model/tables.service';

@Controller('tables')
export class TablesController extends BaseControllerMutable<Tables> {

    constructor(service:TablesService) {
        super(service, '/tables')
    }
}
