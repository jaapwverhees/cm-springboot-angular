import {Stage} from "./Stage";
import {TimeTrailPrediction} from "./TimeTrailPrediction";
import {Team} from "./Team";
import {Score} from "./Score";

export class TimeTrailStage extends Stage {
  teams: Team[];
  scores: Score[];
  preditions: TimeTrailPrediction[];
}
