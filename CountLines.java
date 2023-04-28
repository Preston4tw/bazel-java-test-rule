import com.google.common.base.Preconditions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CountLines {
    public static boolean stringLineCountIsEven(String filename) throws IOException {
        try(Stream<String> s = Files.lines(Path.of(filename))) {
            long count = s.count();
            return count % 2 == 0;
        }
    }

    public static void main(String... args) throws IOException {
        Preconditions.checkNotNull(args[0]);
        if (args.length < 2) {
            System.err.println("Usage: CountLines <even|odd> <files>");
            System.exit(1);
        }
        boolean parity = args[0].equals("even");
        List<String> filenames = Arrays.asList(args).subList(1, args.length);
        int exitcode = 0;
        for (String filename : filenames) {
            boolean b = stringLineCountIsEven(filename);
            if(b != parity) {
                System.out.printf("parity mismatch file:%s isEven:%s parity:%s\n", filename, b, parity);
                exitcode = 1;
            } else {
                System.out.printf("parity match file:%s isEven:%s parity:%s\n", filename, b, parity);
            }
        }
        System.exit(exitcode);
    }
}
