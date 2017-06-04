import java.util.Set;

public interface BinaryRelation<X, Y> {
    //Each binary relation object supports homogeneous relations.
    //All X values are of type X and Y values are of type Y.

    public void addPair(X xValue, Y yValue);
    //Adds a given pair to the relation. If the given pair already
    //exists in the relation, it will not be added.

    public void removePair(X xValue, Y yValue);
    //Removes a given pair from the relation.

    public boolean contains(X xValue, Y yValue);
    //Checks if a given pair already exists in the relation.
    //Returns true if yes, false if otherwise.

    public Set<Y> getValues(X xValue);
    //Returns a set of all Y values that are associated with a given X value.

    public Set<X> getXvalues(Y yValue);
    //Returns a set of all X values that are associated with a given Y value.

    public void clear();
    //Makes the binary relation empty.

    public void removeRelationsByX(X xValue);
    //Removes all relations that contain a given X value.

    public void removeRelationsByY(Y yValue);
    //Removes all relations that contain a given Y value.

    public String toString();
}
