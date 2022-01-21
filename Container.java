public abstract class Container implements Fillable{
    private boolean empty = true;
    private Material material = new Material(true, true);

    private final String name;


    public Container(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public void setMaterial(Material mat){
        this.material = mat;
    }

    public static class Material{
        private final boolean opaque;
        private final boolean fragile;
        public Material(boolean opq, boolean frg){
            opaque = opq;
            fragile = frg;
        }
        public boolean isOpaque(){
            return this.opaque;
        }
        public boolean isFragile(){
            return this.fragile;
        }
    }

    public abstract boolean isFragile();
    public Material getMaterial(){
        return this.material;
    }
    public boolean isOpaque(){
        return this.material.isOpaque();
    }
    @Override
    public boolean isEmpty() {
        return empty;
    }
    @Override
    public void setEmpty(boolean empty){ this.empty = empty; }
    @Override
    public String toString() {
        return name;
    }
}
