import java.util.*;
import static java.util.Collections.*;

public class PackageTest {
    public static void main(String[] args) {
        java.util.List<String> list = new java.util.LinkedList<String>();
        List<Integer> list2 = new ArrayList<>();
        Collections.sort(list, null);
        sort(list2, null);
    }
}
