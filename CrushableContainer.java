public class CrushableContainer extends Container {

    public CrushableContainer(String name, Material material) {
        super(name, material);
    }
    @Override
    public boolean isFragile(){ return this.getMaterial().isFragile(); }
}
