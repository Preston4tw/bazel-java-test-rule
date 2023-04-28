# Bazel java_test macro playground

This is an experiment with writing Bazel [macros](https://bazel.build/extending/macros) on top of
[native.java_test](https://bazel.build/reference/be/java#java_test) rules. I wanted to see how difficult it is to write
Java to support arbitrary test cases.

My early attempts were to try to create rules directly. I had problems getting it working. A macro wrapping
native.java_test seems to more or less do what I want for a prototype. It remains to be seen if I can actually use this.

## The Macro
line_count_matches_parity_test(name, parity, srcs, visibility=None)

```py
filegroup(
    name = "fg1",
    srcs = glob([
        "**/*.txt",
        "*.java"
        ],
        exclude = ["bazel-*/**"],
    ),
)

line_count_matches_parity_test(
    name = "check_line_counts_are_even",
    parity = "even",
    srcs = [:fg1],
)
```

The test checks each file in srcs to see if the line count is even or odd, compared against a parity argument "even" or
"odd". Each file checked outputs a line. The test passes if all files match parity.

The rule is just a native.java_test macro. Check out the files in the repo to see how it works.