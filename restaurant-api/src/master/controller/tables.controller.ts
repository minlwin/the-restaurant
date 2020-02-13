import { Controller } from '@nestjs/common';
import { TablesService } from '../model/tables.service';
import { Tables } from '../model/tables.entity';
import { BaseController } from 'src/common/base.controller';

@Controller('tables')
export class TablesController extends BaseController<Tables> {

    constructor(service:TablesService) {
        super(service, '/tables')
    }

}
