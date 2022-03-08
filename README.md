# GiveFuck
A small terminal application that allows you to give fucks

# Usage
`givefuck` — show usage tooltip

`givefuck maxFucks` — give `maxFucks` fucks

`givefuck maxFucks givenFucks` — give `givenFucks` out of `maxFucks` fucks

# Installing
## automatically

run the following script:
```sh
wget -nv -O - https://github.com/mnemotehnician/givefuck/blob/master/tools/install.sh | bash -
```

## manually
- Clone this repo
- Open the repo dir and run `bash ./gradlew publish`
- Copy the output file (`app/build/libs/givefuck`) to `/usr/bin/`