package Analyseur;

/**
 * Représente différents types de mouvements associés à un score.
 */
public enum Score {
  SFB_MOUV(-2.0),
  Cissor_MOUV(-1.5),
  LSB_MOUV(-1.0),
  SWITCH_MOUV(3.0),
  BEARING_MOUV(4.0),
  REDIRECTION_MOUV(-2.5),
  WORST_MOUV(-5.0),
  SKS_MOUV(-3.0),
  MainEval(-0.1);

  private final double score;

  public double getScore() {
    return score;
  }

  Score(double score) {
    this.score = score;
  }

  /**
   * Retourne la valeur du score selon la nature du mouvement.
   * @param mouv Le mouvement
   * @return Score associé ou 0.0
   */
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
