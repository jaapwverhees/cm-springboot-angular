import {Prediction} from "./Prediction";
import {TimeTrailStage} from "./TimeTrailStage";
import {Team} from "./Team";

export class TimeTrailPrediction extends Prediction {
  stage?: TimeTrailStage;
  team?: Team;
}
