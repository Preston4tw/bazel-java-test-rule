def line_count_matches_parity_test(name, parity, srcs, visibility=None):
    native.java_test(
        name = name,
        size = "small",
        args = [parity] + ["$(locations {})".format(s) for s in srcs],
        visibility = visibility,
        runtime_deps = [
            Label("//:count_lines"),
        ],
        main_class = "CountLines",
        test_class = "CountLines",
        data = srcs
    )