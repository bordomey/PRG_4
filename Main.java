public class Main {
    public static void main(String[] args )
    {
        MainCharacter VP = new MainCharacter("Винни-Пух", Location.TRAP);
        MainCharacter Pii = new MainCharacter( "Пятачок", Location.PYATACHOKHOUSE);
        Character Slonopotam = new Character("Слонопотам", Location.TRAP) {
            @Override
            public void goToLocation(Location goal) {
                this.setCharacterLocation(goal);
            }

            @Override
            public void tryUnstuck(BodyPart bodyPart) {
                this.unstuck(bodyPart);
            }

            @Override
            public void shout(String phrase) {
                return;
            }

            @Override
            public void callCharacter(Character target) {
                return;
            }

            @Override
            public void callHelp() {
                return;
            }

            @Override
            public void scream() {
                return;
            }

            @Override
            public void screamOutLoud(){ return; }
        };
        Container.Material clay = new Container.Material(true, false);
        CrushableContainer Pot = new CrushableContainer("горшок", clay);
        //Definitions
        Pii.setCurrentPlan(Pii.new Plan(Location.SIXPINES, Slonopotam, Location.TRAP));
        Pii.getCurrentPlan().setNextPlan(Pii.new Plan(Location.PYATACHOKHOUSE));
        //Plan
        Pii.goToLocation(Location.WAYTOSIXPINES);
        Pii.getCurrentPlan().checkPlan();
        Pii.goToLocation(Location.SIXPINES);
        Pii.getCurrentPlan().checkPlan();
        Pii.scare();
        Pii.lookIn(Location.TRAP);
        //Pooh
        try {
            VP.stuck(BodyPart.HEAD, Pot);
        }
        catch (BodyPartNotFoundException ex){
            System.err.print(ex);
        }
        VP.tryUnstuck(BodyPart.HEAD);
        VP.shake(BodyPart.HEAD);
        VP.callCharacter(new MainCharacter("Мама", Location.ANYWHERE));
        VP.callHelp();
        VP.scream();
        VP.tryUnstuck(BodyPart.HEAD);
        VP.tryCrush(BodyPart.HEAD);
        VP.goToLocation(Location.ANYWHERE);
        VP.raiseBodyPart(BodyPart.HEAD);
        VP.screamOutLoud();
    }
}
