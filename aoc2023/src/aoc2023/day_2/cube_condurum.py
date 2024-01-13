from pathlib import Path


current_fp = Path(Path(__file__).resolve().parent)
input_path  = current_fp.resolve() / "input"

def get_input_lines():
    with open(input_path, "r") as f:
        lines = [line for line in f]
    return lines

lines = get_input_lines()
print(len(lines))


def parse_game_line(line):
    game_id, rest = line.split(":")
    game_id = game_id.strip()
    rest = rest.strip().replace(" ", "")
    games = rest.split(";")
    return game_id, games


for l in lines:
    print(l)
    parse_game_line(l)
    break


