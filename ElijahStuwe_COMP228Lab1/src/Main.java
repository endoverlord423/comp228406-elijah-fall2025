import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Singers singer1 = new Singers();
        System.out.print(singer1);

        System.out.println("\n\n");

        singer1.SetAll(21, "Tyler Joseph", "Mulberry Street",
                LocalDate.of(1988, 12, 1), 9);
        System.out.print(singer1);

        System.out.println("\n\n");

        singer1.SetId(2);
        System.out.println("ID: " + singer1.GetId());
        singer1.SetName("NF");
        System.out.println("Name: " + singer1.GetName());
        singer1.SetAddress("Michigan");
        System.out.println("Address: " + singer1.GetAddress());
        singer1.SetDateOfBirth(LocalDate.of(1991, 3, 30));
        System.out.println("Date of Birth: " + singer1.GetDateOfBirth());
        singer1.SetAlbumCount(7);
        System.out.println("Album Count: " + singer1.GetAlbumCount());
    }

    public static class Singers {
        private int id = 00;
        private String name = "John";
        private String address = "Space";
        private LocalDate dateOfBirth = LocalDate.now();
        private int albumCount = 0;


        public Singers(){}
        public Singers(int id, String name, String address, LocalDate dateOfBirth, int albumCount) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
            this.albumCount = albumCount;
        }

        public void SetId(int id) { this.id = id; }
        public void SetName(String name) { this.name = name; }
        public void SetAddress(String Address) { this.address = address; }
        public void SetDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
        public void SetAlbumCount(int albumCount) { this.albumCount = albumCount; }
        public void SetAll(int id, String name, String address, LocalDate dateOfBirth, int albumCount)
        {
            this.id = id;
            this.name = name;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
            this.albumCount = albumCount;
        }

        public int GetId() { return id; }
        public String GetName() { return name; }
        public String GetAddress() { return address; }
        public LocalDate GetDateOfBirth() { return dateOfBirth; }
        public int GetAlbumCount() { return albumCount; }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Address: " + address +
                    ", Date of Birth: " + dateOfBirth + ", Album Count: " + albumCount;
        }
    }
}
