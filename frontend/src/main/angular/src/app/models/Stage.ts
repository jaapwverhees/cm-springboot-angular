import {Team} from "./Team";
import {Score} from "./Score";
import {Prediction} from "./Prediction";

export class Stage {
  id: number;
  date: Date;
  teams: Team[];
  scores?: Score[];
  preditions?: Prediction[]
}
