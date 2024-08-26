/**
 * Toggle html class
 *
 * @param className
 */
export function toggleHtmlClass(className) {
    function add() {
        document.documentElement.classList.add(className);
    }

    function remove() {
        document.documentElement.classList.remove(className);
    }

    return {
        add,
        remove
    };
}
