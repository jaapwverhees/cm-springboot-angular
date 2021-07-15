import {GameRequest} from "./GameRequest";

export class CreateKnockoutRequest {
  name: string
  maxDate: Date
  matches: GameRequest[];
}
