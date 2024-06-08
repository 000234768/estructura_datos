{ pkgs }: {
    deps = [
      pkgs.run
      pkgs.imagemagick6
        pkgs.graalvm17-ce
        pkgs.maven
        pkgs.replitPackages.jdt-language-server
        pkgs.replitPackages.java-debug
    ];
}