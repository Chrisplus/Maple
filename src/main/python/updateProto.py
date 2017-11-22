from urllib.request import urlretrieve

def updateHSEnum():
    urlretrieve("https://raw.githubusercontent.com/HearthSim/python-hearthstone/master/hearthstone/enums.py", filename='hsenums.py')


def print_enums(enums, format, linefmt="\t%s = %i,"):
    ret = []
    for enum in sorted(enums):
            sorted_pairs = sorted(enums[enum].items(), key=lambda k: k[1])
            lines = "\n".join(linefmt % (name, value) for name, value in sorted_pairs)
            ret.append(format % (enum, lines))
    print("\n\n".join(ret))

def print_enums_multiple_files(enums, format, ext, linefmt="\t%s = %i,", rp='./'):
        ret = []
        for enum in sorted(enums):
            sorted_pairs = sorted(enums[enum].items(), key=lambda k: k[1])
            # lines = "\n".join(linefmt % (name, value) for name, value in sorted_pairs)

            # linearrays = []
        
            # for name, value in sorted_pairs:
            #     if value >= 0:
            #         linearrays.append(linefmt % (name, value))
            #     else:
            #         linearrays.append(linefmt % (name, (1<<31) + value))

            for i in range(len(sorted_pairs)):
                v = sorted_pairs[i][1]
                if v < 0:
                    sorted_pairs[i] = (sorted_pairs[i][0], (1<<31)+v)

            sorted_pairs = sorted(sorted_pairs, key=lambda x:x[1])
            
            if sorted_pairs and sorted_pairs[0][1] != 0:
                offset = sorted_pairs[0][1]
                
                for i in range(len(sorted_pairs)):
                     sorted_pairs[i] = (sorted_pairs[i][0], sorted_pairs[i][1] - offset)
            
            dup = False
            for i in range(len(sorted_pairs)):
                if i != 0 and sorted_pairs[i][1] == sorted_pairs[i-1][1]:
                    dup = True
                    break
            
            lines = "\n".join(linefmt % (enum.upper() + '_' + name.upper(), value) for name, value in sorted_pairs)
            
            if dup:
                lines = "\toption allow_alias = true;\n" + lines

            ret.append(ret.append(format % (enum, lines)))
            ret.append('\n')
            with open(rp + enum+ '.' + ext,'w') as f:
                for res in ret:
                    if res:
                        f.write(res)
            ret = []

proto3_format = ('syntax = \'proto3\';\n'
                'option java_package = \'com.chrisplus.maple.simulation.enums\';\n'
                '\n'
                'enum %s {\n%s\n\n}')

path = '../proto/'

"""
This script (with args --proto) fetch the enums from python-hearthstone (https://github.com/HearthSim/python-hearthstone), the convert the enums into proto3 format. According to the encoding of proto3, negative values v would transform into (1<<31 + v), hopefully the num size is satisfied.
"""
if __name__ == '__main__':
    updateHSEnum()
    
    import sys
    import json
    from datetime import datetime
    from enum import IntEnum
    from hsenums import *

    enums = {
        k: dict(v.__members__) for k, v in globals().items() if (
        isinstance(v, type) and issubclass(v, IntEnum) and k != "IntEnum"
    )
    }

    if len(sys.argv) >= 2:
        format = sys.argv[1]
    else:
        format = "--json"

    if format == "--ts":
        print_enums(enums, "export const enum %s {\n%s\n}")
    elif format == "--cs":
        print_enums(enums, "public enum %s {\n%s\n}")
    elif format == "--java":
        print_enums(enums, "public enum %s {\n%s\n}", linefmt="\t%s(%i),")
    elif format == '--proto':
        print_enums_multiple_files(enums, proto3_format, 'proto', linefmt="\t%s = %i;", rp=path)
    else:
        print(json.dumps(enums, sort_keys=True))