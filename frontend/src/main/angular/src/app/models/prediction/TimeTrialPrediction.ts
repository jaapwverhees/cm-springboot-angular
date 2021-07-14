import {Prediction} from "./Prediction";
import {TimeTrialStage} from "../competition/TimeTrialStage";
import {Team} from "../competition/Team";

export class TimeTrialPrediction extends Prediction {
  stage?: TimeTrialStage;
  team?: Team;
}
