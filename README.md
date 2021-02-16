# Diff

Diff is a library written in Scala that calculate the difference between two strings and produce output string that contain information about what get added and what get subtracted in new string. It uses Myers Algorithm to compute diff (Read more about [diff](https://en.wikipedia.org/wiki/Diff))


### Usage

Call `generate` method on `Diff` object and pass old and new String as parameter

```scala
val diff = Diff.generate("ABC", "AECD")

diff.stringify()  // output: A-B+EC+D
```

### Examples
```scala
Diff.generate("Hi from old String", "Hello from new String").stringify

"H-i+e+l+l+o from -o-l-d+n+e+w String" //output
```

### Future Improvements

- Improve stringify function to collect all consecutive `+, -` operation and show as single `+, -` operation

