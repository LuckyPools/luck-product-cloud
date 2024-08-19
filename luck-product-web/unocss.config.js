// uno.config.js
import {
    defineConfig,
    presetAttributify,
    presetUno,
    transformerVariantGroup,
} from "unocss";
import presetIcons from "@unocss/preset-icons";
export default defineConfig({
    presets: [
        presetAttributify(),
        presetUno(),
        presetIcons({
            /* options */
        }),
    ],
    transformers: [transformerVariantGroup()],
});

