/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int sizeInStorage = 0;

    void clear() {
        for (int i = 0; i < sizeInStorage; i++) {
            storage[i] = null;
        }
        sizeInStorage = 0;
    }

    void save(Resume r) {
        storage[sizeInStorage] = r;
        sizeInStorage++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < sizeInStorage; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < sizeInStorage; i++) {
            if (storage[i].uuid == uuid) {
                sizeInStorage--;
                if (sizeInStorage > 0) {
                    storage[i] = storage[sizeInStorage];
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] ret = new Resume[sizeInStorage];
        System.arraycopy(storage, 0, ret, 0, sizeInStorage);
        return ret;
    }

    int size() {
        return sizeInStorage;
    }
}
