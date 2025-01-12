package Analyseur;

public enum Score {
  SFB_MOUV(2.5),
  Cissor_MOUV(-0.5),
  LSB_MOUV(-0.3),
  SWITCH_MOUV(-0.2),
  BEARING_MOUV(-0.1),
  REDIRECTION_MOUV(-0.1),
  WORST_MOUV(-0.2),
  SKS_MOUV(1),
  MainEval(-0.2);

  private final double score;

  public double getScore() {
    return score;
  }

  Score(double score) {
    this.score = score;
  }

  public static double correspond_score(String mouv) {
    switch (mouv) {
      case "SFB":
        return SFB_MOUV.getScore();
      case "Cissors":
        return Cissor_MOUV.getScore();
      case "LSB":
        return LSB_MOUV.getScore();
      case "SWITCH":
        return SWITCH_MOUV.getScore();
      case "BEARING":
        return BEARING_MOUV.getScore();
      case "REDIRECTION":
        return REDIRECTION_MOUV.getScore();
      case "WORST":
        return WORST_MOUV.getScore();
      case "SKS":
        return SKS_MOUV.getScore();
      case "MainEval":
        return MainEval.getScore();
      default:
        return 0.0;
    }
  }
}
