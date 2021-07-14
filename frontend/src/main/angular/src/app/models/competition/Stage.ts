import {Team} from "./Team";
import {Score} from "./Score";
import {Prediction} from "../prediction/Prediction";

export class Stage {
  id: number;
  date: Date;
  scores?: Score[];
}
