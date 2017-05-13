package operation;

import operation.section.Operator;

/**
 * Created by liaoyilin on 5/12/17.
 */
public abstract class Section {

    private final String element;

    public Section(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    @Override
    public String toString() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator)) return false;

        Section section = (Section) o;

        return element != null ? element.equals(section.toString()) : section.element == null;
    }

    @Override
    public int hashCode() {
        return element != null ? element.hashCode() : 0;
    }

}
