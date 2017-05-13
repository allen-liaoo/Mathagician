package operation;

import operation.Section;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liaoyilin on 5/12/17.
 */
public class Operation {

    List<Section> operation;

    public Operation(Section... sections) {
        operation = Arrays.asList(sections);
    }

}
