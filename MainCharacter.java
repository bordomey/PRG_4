import java.util.Random;

public class MainCharacter extends Character {
    public MainCharacter(){
        super();
    }

    public MainCharacter(String name, Location location){
        super(name, location);
    }

    public void shake(BodyPart part) {
        System.out.print(this.toString() + " тряс " + part.toString() + ". ");
        if(isStucked(part)){
            setStuckness(part, getStuckness(part) + 1);
        }
    }

    public void tryCrush(BodyPart part){
        System.out.print(this.toString() + " пытался ударится обо что-нибудь и");
        if(this.isBlinded()){
            System.out.print(" не смог так-как ничего не видел. ");
        }
        else {
            if (getStuckedContainer(part) instanceof CrushableContainer) {
                if (!(this.getStuckedContainer(part).isFragile())) {
                    System.out.print(" не смог разбить " + this.getStuckedContainer(part).toString() + ". ");
                } else {
                    System.out.print(" разбил " + this.getStuckedContainer(part).toString() + ". ");
                    unstuck(part);
                }
            }
        }
    }

    public void scare(){
        scream();
        System.out.print("Ему хотелось убежать. ");
        if(this.getCurrentPlan().checkSuccess()){
            System.out.print("И он убежал. ");
        }
        else{
            System.out.print("Но он не мог, он хотел посмотреть есть ли " + this.getCurrentPlan().getLookForCharacter()
                    + " в " + this.getCurrentPlan().getLookForLocation() + ". ");
        }
    }

    @Override
    public void goToLocation(Location goal) {
        if(getCharacterLocation().hardToGetOut && isBlinded()){
            System.out.print("Не смог выбраться из " + this.getCharacterLocation().toString() + " так как ничего не видел. ");
        }
        else
            setCharacterLocation(goal);
    }

    @Override
    public void shout(String phrase){
        class Sentence{
            private Character subject;
            private String phrase;
            private String[] verb = {"кричал", "крикнул", "выкрикнул"};
            public Sentence(Character subj, String phrase){
                this.subject = subj;
                this.phrase = phrase;
            }
            public String compile(){
                return subject.toString() + " " + verb[(int)Math.round(Math.random()*2)] + " " + phrase + ". ";
            }
        }
        System.out.print(new Sentence(this, phrase).compile());
    }

    @Override
    public void callCharacter(Character target) {
        shout(target.toString() + "!");
    }

    @Override
    public void callHelp() {
        shout("Помогите!");
    }

    @Override
    public void scream() {
        shout("Ай-ай-ай!");
    }

    @Override
    public void screamOutLoud() {
        System.out.print(this.toString() + " издал жалобный вопль! ");
    }

    @Override
    public void tryUnstuck(BodyPart bodyPart) {
        if(isStucked(bodyPart)) {
            if(getStuckness(bodyPart) > 0)
                System.out.print(this.toString() + " не мог снять " + getStuckedContainer(bodyPart) + " c " + bodyPart.toString() + ". ");
            else {
                System.out.print(this.toString() + " снял " + getStuckedContainer(bodyPart) + " c " + bodyPart.toString() + ". ");
                this.unstuck(bodyPart);
            }
        }
    }


}
