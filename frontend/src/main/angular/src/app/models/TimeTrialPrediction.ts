import {Prediction} from "./Prediction";
import {TimeTrialStage} from "./TimeTrialStage";
import {Team} from "./Team";

export class TimeTrialPrediction extends Prediction {
  stage?: TimeTrialStage;
  team?: Team;
}
