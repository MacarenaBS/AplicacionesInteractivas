package usuarios;
import java.util.List;
import reclamos.Reclamo;
public abstract class Rol
{
	protected List<Usuario> colUsuarios;
	protected List<Reclamo> colReclamos;
	public abstract void administrarReclamo(String strNumero, String strDescipcion);
	public abstract void cerrarReclamo(String strNumero);
	public void addUsuario(Usuario us)
	{
		if (!this.colUsuarios.contains(us))
		{
			this.colUsuarios.add(us);
		}
	}	
	public void removeUsuario(Usuario us)
	{
		for (Usuario usuario : colUsuarios)
		{
			if (us.getUsername().equals(usuario.getUsername()))
			{
				this.colUsuarios.remove(us);
			}
		}
	}
}