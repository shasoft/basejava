/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int countResumes;

    void clear() {
        for (int i = 0; i < countResumes; i++) {
            storage[i] = null;
        }
        countResumes = 0;
    }

    void save(Resume r) {
        storage[countResumes] = r;
        countResumes++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].uuid == uuid) {
                countResumes--;
                if (countResumes > 0) {
                    storage[i] = storage[countResumes];
                }
                storage[countResumes] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[countResumes];
        System.arraycopy(storage, 0, allResume, 0, countResumes);
        return allResume;
    }

    int size() {
        return countResumes;
    }
}
