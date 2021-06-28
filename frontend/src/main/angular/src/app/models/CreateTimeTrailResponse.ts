import {Stage} from "./Stage";
import {TimeTrailStage} from "./TimeTrailStage";
import {Team} from "./Team";
import {Score} from "./Score";
import {Prediction} from "./Prediction";

class ProtoStage {
  teams: Team[];
}

export class CreateTimeTrailResponse {
  stages?: ProtoStage[]
}
