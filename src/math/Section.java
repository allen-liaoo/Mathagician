package math;

/**
 * General sections of a math operation
 * @author Alien Ideology <alien.ideology at alien.org>
 */
public class Section {

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
        Section section = (Section) o;
        return this.section != null ? this.section.equals(section.toString()) : section.section == null;
    }

    @Override
    public int hashCode() {
        return section != null ? section.hashCode() : 0;
    }

}
