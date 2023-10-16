# Ray Tracing

<img src="https://github.com/TwentyFiveSoftware/ray-tracing-gpu/blob/master/sceneRender.png">

## Overview

This is my take on [Peter Shirley's Ray Tracing in One Weekend](https://github.com/RayTracing/raytracing.github.io) book.

This project uses [Java](https://www.oracle.com/java/) and is intended to serve as a reference point in the performance comparison (see below).

## Build & Run this project

1. Clone the repository
2. Optional: Change sample and thread count in `src/main/java/dev/twentyfive/raytracing/RayTracing.java`
3. Build the project
   ```sh
   ./gradlew jar
   ```
4. Run the jar file
   ```sh
   java -jar ./build/libs/java-ray-tracing-1.0.0.jar
   ```

## Performance

I've already implemented Peter Shirley's ray tracing in various programming languages running on CPU & GPU and compared their performance.

The performance was measured on the same scene (see image above) with the same amount of objects, the same recursive
depth, the same resolution (1920 x 1080). The measured times are averaged over multiple runs.

*Reference system: AMD Ryzen 9 5900X (12 Cores / 24 Threads) | AMD Radeon RX 6800 XT*

|                                                                                                    | 1 sample / pixel | 100 samples / pixel | 10,000 samples / pixel | 
|----------------------------------------------------------------------------------------------------|-----------------:|--------------------:|-----------------------:|
| [Elixir](https://github.com/TwentyFiveSoftware/elixir-ray-tracing)                                 |      67,200.0 ms |                 N/A |                    N/A |
| [Java](https://github.com/TwentyFiveSoftware/java-ray-tracing)                                     |      10,500.0 ms |                 N/A |                    N/A |
| [Go](https://github.com/TwentyFiveSoftware/go-ray-tracing)                                         |       2,490.0 ms |             250.0 s |                    N/A |
| [C++](https://github.com/TwentyFiveSoftware/ray-tracing)                                           |         685.0 ms |              70.1 s |                    N/A |
| [Rust](https://github.com/TwentyFiveSoftware/rust-ray-tracing)                                     |         640.0 ms |              66.1 s |                    N/A |
| [C](https://github.com/TwentyFiveSoftware/c-ray-tracing)                                           |         329.0 ms |              32.8 s |                    N/A |
| [GPU - Compute Shader](https://github.com/TwentyFiveSoftware/ray-tracing-gpu)                      |          21.5 ms |               2.1 s |                215.0 s |
| [GPU - Vulkan Ray Tracing Extension](https://github.com/TwentyFiveSoftware/ray-tracing-gpu-vulkan) |           1.2 ms |               0.1 s |                 12.5 s |
