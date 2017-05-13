package operation;

import operation.entities.Operator;

/**
 * Created by liaoyilin on 5/12/17.
 */
public abstract class Section {

    private final String section;

    public Section(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    @Override
    public String toString() {
        return section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator)) return false;

        Section section = (Section) o;

        return this.section != null ? this.section.equals(section.toString()) : section.section == null;
    }

    @Override
    public int hashCode() {
        return section != null ? section.hashCode() : 0;
    }

}
