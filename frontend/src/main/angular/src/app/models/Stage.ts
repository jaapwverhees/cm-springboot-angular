import {Team} from "./Team";
import {Score} from "./Score";
import {Prediction} from "./Prediction";

export class Stage {
  teams: Team[];
  scores?: Score[];
  preditions?: Prediction[]
}
